//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sbc.eia.idl.iomip_types;

import org.omg.CORBA.portable.IDLEntity;

public final class Products implements IDLEntity {
    public String aName = null;
    public String[] aValue = null;

    public Products() {
    }

    public Products(String _aName, String[] _aValue) {
        this.aName = _aName;
        this.aValue = _aValue;
    }
}
