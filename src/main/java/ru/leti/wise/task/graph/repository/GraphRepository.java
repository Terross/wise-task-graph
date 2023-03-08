package ru.leti.wise.task.graph.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.leti.wise.task.graph.domain.Graph;

import java.util.UUID;

public interface GraphRepository extends ReactiveCrudRepository<Graph, UUID> {

}
