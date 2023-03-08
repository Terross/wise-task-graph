package ru.leti.wise.task.graph.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.leti.GraphOuterClass;
import ru.leti.GraphOuterClass.CreateGraphResponse;
import ru.leti.wise.task.graph.mapper.GraphMapper;
import ru.leti.wise.task.graph.repository.GraphRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateGraphOperation {

    private final GraphRepository graphRepository;
    private final GraphMapper graphMapper;

    public Mono<CreateGraphResponse> activate(GraphOuterClass.Graph graph) {
        return graphRepository.save(graphMapper.graphRequestToGraph(graph))
                .map((__) -> CreateGraphResponse
                        .newBuilder()
                        .setGraph(graph)
                        .build());
    }
}
