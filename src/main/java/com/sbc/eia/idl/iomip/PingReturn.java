//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sbc.eia.idl.iomip;

import com.sbc.eia.idl.bis_types.BisContext;
import org.omg.CORBA.portable.IDLEntity;

public final class PingReturn implements IDLEntity {
    public BisContext aBisContext = null;

    public PingReturn() {
    }

    public PingReturn(BisContext _aBisContext) {
        this.aBisContext = _aBisContext;
    }
}
