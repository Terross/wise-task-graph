package ru.leti.wise.task.graph.logic;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.GraphGrpc.GetGraphByIdResponse;
import ru.leti.wise.task.graph.domain.Graph;
import ru.leti.wise.task.graph.mapper.GraphMapper;
import ru.leti.wise.task.graph.repository.GraphRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetGraphByIdOperation {

    private final GraphMapper graphMapper;
    private final GraphRepository graphRepository;

    public Mono<GetGraphByIdResponse> activate(UUID id) {
        return graphRepository.findById(id)
                .map(this::createResponse)
                .switchIfEmpty(Mono.error(new StatusRuntimeException(Status.NOT_FOUND)));
    }

    private GetGraphByIdResponse createResponse(Graph graph) {
        return GetGraphByIdResponse
                .newBuilder()
                .setGraph(graphMapper.graphToGraphResponse(graph))
                .build();
    }
}
