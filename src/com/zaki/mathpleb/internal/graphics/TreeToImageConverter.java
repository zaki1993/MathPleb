package com.zaki.mathpleb.internal.graphics;

import com.zaki.mathpleb.internal.tree.Node;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class TreeToImageConverter {

    public void generateImageFromTree(Node tree) throws IOException {

        // construct nodes
        List<LinkSource> nodes = constructNodesRecursively(tree, UUID.randomUUID().toString());

        // add nodes with directions
        Graph g = graph("preview").directed().with(nodes);

        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("preview/input.png"));
    }

    private List<LinkSource> constructNodesRecursively(Node tree, String id) {

        List<LinkSource> links = new ArrayList<>();

        guru.nidi.graphviz.model.Node current = constructNode(tree, id);
        if (tree.getLeft() != null) {
            String randId = UUID.randomUUID().toString();
            links.add(current.link(constructNode(tree.getLeft(), randId)));
            links.addAll(constructNodesRecursively(tree.getLeft(), randId));
        }
        if (tree.getRight() != null) {
            String randId = UUID.randomUUID().toString();
            links.add(current.link(constructNode(tree.getRight(), randId)));
            links.addAll(constructNodesRecursively(tree.getRight(), randId));
        }

        return links;
    }

    private guru.nidi.graphviz.model.Node constructNode(Node plebNode, String id) {
        return node(id).with(Label.html("<b>" + plebNode.getPlebObject().toString() + "</b>"), Color.rgb("1020d0").font());
    }
}
