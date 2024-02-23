package ru.leti.wise.task.graph.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.GraphGrpc.GetGraphLibraryResponse;
import ru.leti.wise.task.graph.mapper.GraphMapper;
import ru.leti.wise.task.graph.repository.GraphRepository;

@Component
@RequiredArgsConstructor
public class GetGraphLibraryOperation {

    private final GraphRepository graphRepository;
    private final GraphMapper graphMapper;

    public Mono<GetGraphLibraryResponse> activate() {
        return graphRepository.findAllByIsNamedIsTrue()
                .collectList()
                .map(graphs -> GetGraphLibraryResponse.newBuilder()
                        .addAllGraphList(graphMapper.toGraphs(graphs))
                        .build());
    }
}
