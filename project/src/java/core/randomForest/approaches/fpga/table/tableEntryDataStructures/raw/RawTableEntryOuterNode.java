package project.src.java.core.randomForest.approaches.fpga.table.tableEntryDataStructures.raw;

public class RawTableEntryOuterNode extends RawTableEntry {
    private Integer nodeClass;

    public RawTableEntryOuterNode(Integer id, Integer nodeClass) {
        this.id = id;
        this.nodeClass = nodeClass;
    }
    public Integer getNodeClass(){ return this.nodeClass; }
}
