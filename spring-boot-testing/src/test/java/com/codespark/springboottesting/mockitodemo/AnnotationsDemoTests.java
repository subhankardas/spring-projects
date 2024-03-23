package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

@ExtendWith(MockitoExtension.class)
public class AnnotationsDemoTests {

    @Mock // 1. Create mock object of ADTService
    private ADTService mockService;

    @InjectMocks // 2. Inject mock objects into ADTBusiness
    private ADTBusiness business;

    @Captor // 3. Create ArgumentCaptor to capture method arguments
    private ArgumentCaptor<Integer> firstCaptor;

    @Captor
    private ArgumentCaptor<Integer> secondCaptor;

    @Spy // 4. Create spy object of ADTBusiness
    private ADTBusiness businessSpy;

    @Test
    void test_BDD() {
        // GIVEN
        when(mockService.doTask(2, 4)).thenReturn(6);

        // WHEN
        int result = business.getSum(1, 2);

        // THEN
        assertEquals(6, result);
        verify(mockService).doTask(firstCaptor.capture(), secondCaptor.capture()); // Capture method arguments

        assertEquals(2, firstCaptor.getValue());
        assertEquals(4, secondCaptor.getValue());

        assertEquals(6, businessSpy.getSum(1, 2));
        verify(businessSpy, times(1)).getSum(1, 2); // Verify that method was called once with given arguments
    }

}

@Component
class ADTBusiness {
    private final ADTService service;

    ADTBusiness() { // Required for using @Spy to create instance
        this(new ADTServiceImpl());
    }

    public ADTBusiness(ADTService service) {
        this.service = service;
    }

    public int getSum(int inp1, int inp2) {
        return service.doTask(inp1 + 1, inp2 + 2);
    }
}

interface ADTService {
    public int doTask(int inp1, int inp2);
}

@Component
class ADTServiceImpl implements ADTService {
    @Override
    public int doTask(int inp1, int inp2) {
        return inp1 + inp2;
    }
}