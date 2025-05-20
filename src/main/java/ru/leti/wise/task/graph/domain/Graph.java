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
    private UUID authorId;

    private int vertexCount;
    private int edgeCount;
    private Boolean isDirect;
    private String name;
    private Boolean isNamed;
    private List<Edge> edgeList;
    private List<Vertex> vertexList;
}
