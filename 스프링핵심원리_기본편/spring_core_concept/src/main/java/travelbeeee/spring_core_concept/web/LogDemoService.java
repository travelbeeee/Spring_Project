package travelbeeee.spring_core_concept.web;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import travelbeeee.spring_core_concept.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log(id);
    }
}
