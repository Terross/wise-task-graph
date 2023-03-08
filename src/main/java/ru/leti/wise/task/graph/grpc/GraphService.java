package ru.leti.wise.task.graph.grpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;
import ru.leti.GraphOuterClass.CreateGraphRequest;
import ru.leti.GraphOuterClass.CreateGraphResponse;
import ru.leti.GraphOuterClass.GetGraphByIdRequest;
import ru.leti.GraphOuterClass.GetGraphByIdResponse;
import ru.leti.ReactorGraphServiceGrpc;
import ru.leti.wise.task.graph.logic.CreateGraphOperation;
import ru.leti.wise.task.graph.logic.GetGraphByIdOperation;

import static java.util.UUID.fromString;

@Slf4j
@GRpcService
@RequiredArgsConstructor
public class GraphService extends ReactorGraphServiceGrpc.GraphServiceImplBase {

    private final GetGraphByIdOperation getGraphByIdOperation;
    private final CreateGraphOperation createGraphOperation;

    @Override
    public Mono<GetGraphByIdResponse> getGraphById(Mono<GetGraphByIdRequest> request) {
        return request.flatMap(req -> getGraphByIdOperation.activate(fromString(req.getId())));
    }

    @Override
    public Mono<CreateGraphResponse> createGraph(Mono<CreateGraphRequest> request) {
        return request.flatMap(req -> createGraphOperation.activate(req.getGraph()));
    }
}
