package ru.leti.wise.task.graph.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphGrpc.IsOwnerGraphRequest;
import ru.leti.wise.task.graph.GraphGrpc.IsOwnerGraphResponse;
import ru.leti.wise.task.graph.domain.Graph;
import ru.leti.wise.task.graph.mapper.GraphMapper;
import ru.leti.wise.task.graph.repository.GraphRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IsOwnerGraphOperation {

    private final GraphMapper graphMapper;
    private final GraphRepository graphRepository;

    public Mono<GraphGrpc.IsOwnerGraphResponse> activate(IsOwnerGraphRequest request) {
        String userId = request.getUserId();
        return graphRepository.findById(UUID.fromString(request.getGraphId()))
                .map(graph -> graph.getAuthorId().equals(userId))
                .map(this::createResponse)
                .defaultIfEmpty(createResponse(false));
    }

    private GraphGrpc.IsOwnerGraphResponse createResponse(boolean isOwner) {
        return GraphGrpc.IsOwnerGraphResponse
                .newBuilder()
                .setResult(isOwner)
                .build();
    }
}


