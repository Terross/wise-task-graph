package ru.leti.wise.task.graph.grpc;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.GraphGrpc.CreateGraphRequest;
import ru.leti.wise.task.graph.GraphGrpc.CreateGraphResponse;
import ru.leti.wise.task.graph.GraphGrpc.GetGraphByIdRequest;
import ru.leti.wise.task.graph.GraphGrpc.GetGraphByIdResponse;
import ru.leti.wise.task.graph.GraphGrpc.GenerateGraphRequest;
import ru.leti.wise.task.graph.GraphGrpc.GenerateGraphResponse;
import ru.leti.wise.task.graph.ReactorGraphServiceGrpc;
import ru.leti.wise.task.graph.logic.CreateGraphOperation;
import ru.leti.wise.task.graph.logic.GenerateRandomGraphOperation;
import ru.leti.wise.task.graph.logic.GetGraphByIdOperation;
import ru.leti.wise.task.graph.util.LogGrpcInterceptor;

import static java.util.UUID.fromString;

@Slf4j
@Observed
@GRpcService(interceptors = { LogGrpcInterceptor.class })
@RequiredArgsConstructor
public class GraphService extends ReactorGraphServiceGrpc.GraphServiceImplBase {

    private final GetGraphByIdOperation getGraphByIdOperation;
    private final CreateGraphOperation createGraphOperation;
    private final GenerateRandomGraphOperation generateRandomGraphOperation;

    @Override
    public Mono<GetGraphByIdResponse> getGraphById(Mono<GetGraphByIdRequest> request) {
        return request.flatMap(req -> getGraphByIdOperation.activate(fromString(req.getId())));
    }

    @Override
    public Mono<CreateGraphResponse> createGraph(Mono<CreateGraphRequest> request) {
        return request.flatMap(req -> createGraphOperation.activate(req.getGraph()));
    }

    @Override
    public Mono<GenerateGraphResponse> generateRandomGraph(Mono<GenerateGraphRequest> request) {
        return request.flatMap(generateRandomGraphOperation::activate);
    }
}
