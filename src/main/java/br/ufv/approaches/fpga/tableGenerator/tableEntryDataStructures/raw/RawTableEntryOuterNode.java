package br.ufv.approaches.fpga.tableGenerator.tableEntryDataStructures.raw;

import br.ufv.approaches.fpga.tableGenerator.tableEntryDataStructures.raw.RawTableEntry;

public class RawTableEntryOuterNode extends RawTableEntry {
    private Integer nodeClass;

    public RawTableEntryOuterNode(Integer id, Integer nodeClass) {
        this.id = id;
        this.nodeClass = nodeClass;
    }
    public Integer getNodeClass(){ return this.nodeClass; }
}
