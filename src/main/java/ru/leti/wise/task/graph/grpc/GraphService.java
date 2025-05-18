package ru.leti.wise.task.graph.grpc;

import com.google.protobuf.Empty;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.GraphGrpc.*;
import ru.leti.wise.task.graph.ReactorGraphServiceGrpc;
import ru.leti.wise.task.graph.logic.*;
import ru.leti.wise.task.graph.util.LogGrpcInterceptor;

import static java.util.UUID.fromString;

@Slf4j
@Observed
@GRpcService(interceptors = {LogGrpcInterceptor.class})
@RequiredArgsConstructor
public class GraphService extends ReactorGraphServiceGrpc.GraphServiceImplBase {

    private final IsOwnerGraphOperation isOwnerGraphOperation;
    private final GetGraphByIdOperation getGraphByIdOperation;
    private final CreateGraphOperation createGraphOperation;
    private final GenerateRandomGraphOperation generateRandomGraphOperation;
    private final GetGraphLibraryOperation getGraphLibraryOperation;
    private final RemoveGraphOperation removeGraphOperation;

    @Override
    public Mono<IsOwnerGraphResponse> isOwnerGraph(Mono<IsOwnerGraphRequest> request) {
        return request.flatMap(isOwnerGraphOperation::activate);
    }

    @Override
    public Mono<GetGraphByIdResponse> getGraphById(Mono<GetGraphByIdRequest> request) {
        return request.flatMap(req -> getGraphByIdOperation.activate(fromString(req.getId())));
    }

    @Override
    public Mono<CreateGraphResponse> createGraph(Mono<CreateGraphRequest> request) {
        return request.flatMap(createGraphOperation::activate);
    }

    @Override
    public Mono<GenerateGraphResponse> generateRandomGraph(Mono<GenerateGraphRequest> request) {
        return request.flatMap(generateRandomGraphOperation::activate);
    }

    @Override
    public Mono<GetGraphLibraryResponse> getGraphLibrary(Mono<Empty> request) {
        return request.flatMap((__) -> getGraphLibraryOperation.activate());
    }

    @Override
    public Mono<RemoveGraphResponse> removeGraph(Mono<RemoveGraphRequest> request) {
        return request.flatMap(removeGraphOperation::activate);
    }
}
