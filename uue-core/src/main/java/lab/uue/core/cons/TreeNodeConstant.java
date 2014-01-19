package lab.uue.core.cons;

import lab.uue.core.annotation.MetaData;

public class TreeNodeConstant {
    
    public static enum TreeNodeDragType {

        @MetaData(title = "成为子节点")
        inner,

        @MetaData(title = "成为同级前一个节点")
        prev,

        @MetaData(title = "成为同级后一个节点")
        next;

    }
}
