package ru.leti.wise.task.graph.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class Graph {

    @Id
    private UUID id;

    private int vertexCount;
    private int edgeCount;
    private boolean isDirect;
    private List<Edge> edgeList;
    private List<Vertex> vertexList;
}
