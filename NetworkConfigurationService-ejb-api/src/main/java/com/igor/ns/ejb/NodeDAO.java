package com.igor.ns.ejb;

import com.igor.ns.entity.Node;

import javax.ejb.Local;
import java.util.List;

@Local
public interface NodeDAO {

    Node getNodeById(final long id);

    List<Node> getNodes();

    boolean persistNode(final Node node);

    void delete(final long id);
}
