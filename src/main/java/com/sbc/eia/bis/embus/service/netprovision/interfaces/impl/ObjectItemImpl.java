//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces.impl;

public class ObjectItemImpl implements com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem, java.io.Serializable, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.ValidatableObject
{

    private final static long serialVersionUID = 5000L;
    protected boolean has_Index;
    protected int _Index;
    protected com.sun.xml.bind.util.ListImpl _Element = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    protected com.sun.xml.bind.util.ListImpl _Object = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    protected java.lang.String _Name;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem.class);
    }

    public int getIndex() {
        return _Index;
    }

    public void setIndex(int value) {
        _Index = value;
        has_Index = true;
    }

    public boolean isSetIndex() {
        return has_Index;
    }

    public void unsetIndex() {
        has_Index = false;
    }

    public java.util.List getElement() {
        return _Element;
    }

    public boolean isSetElement() {
        return _Element.isModified();
    }

    public void unsetElement() {
        _Element.clear();
        _Element.setModified(false);
    }

    public java.util.List getObject() {
        return _Object;
    }

    public boolean isSetObject() {
        return _Object.isModified();
    }

    public void unsetObject() {
        _Object.clear();
        _Object.setModified(false);
    }

    public java.lang.String getName() {
        return _Name;
    }

    public void setName(java.lang.String value) {
        _Name = value;
    }

    public boolean isSetName() {
        return (_Name!= null);
    }

    public void unsetName() {
        _Name = null;
    }

    public com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _Element.size();
        int idx3 = 0;
        final int len3 = _Object.size();
        context.startElement("", "Name");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Name), "Name");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        while (idx2 != len2) {
            context.startElement("", "Element");
            int idx_2 = idx2;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Element.get(idx_2 ++)), "Element");
            context.endNamespaceDecls();
            int idx_3 = idx2;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Element.get(idx_3 ++)), "Element");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Element.get(idx2 ++)), "Element");
            context.endElement();
        }
        while (idx3 != len3) {
            context.startElement("", "Object");
            int idx_4 = idx3;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Object.get(idx_4 ++)), "Object");
            context.endNamespaceDecls();
            int idx_5 = idx3;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Object.get(idx_5 ++)), "Object");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Object.get(idx3 ++)), "Object");
            context.endElement();
        }
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _Element.size();
        int idx3 = 0;
        final int len3 = _Object.size();
        if (has_Index) {
            context.startAttribute("", "Index");
            try {
                context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _Index)), "Index");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        while (idx2 != len2) {
            idx2 += 1;
        }
        while (idx3 != len3) {
            idx3 += 1;
        }
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _Element.size();
        int idx3 = 0;
        final int len3 = _Object.size();
        while (idx2 != len2) {
            idx2 += 1;
        }
        while (idx3 != len3) {
            idx3 += 1;
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0007As\u00fappsq\u0000~\u0000\u0000\u0005AM\u00e8ppsq\u0000~\u0000\u0000\u0003"
+"\u00c1\u00a0hppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L"
+"\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv"
+".grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL"
+"\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0002[\u00ad\u00afpp\u0000sq\u0000~\u0000\u0000\u0002[\u00ad\u00a4ppsr\u0000\u001bcom.sun.msv."
+"grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Data"
+"type;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq"
+"\u0000~\u0000\u0003\u0000x|&ppsr\u0000\'com.sun.msv.datatype.xsd.MaxLengthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0001I\u0000\tmaxLengthxr\u00009com.sun.msv.datatype.xsd.DataTypeWithVal"
+"ueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.Dat"
+"aTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFla"
+"gL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fc"
+"oncreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tface"
+"tNamet\u0000\u0012Ljava/lang/String;xr\u0000\'com.sun.msv.datatype.xsd.XSDat"
+"atypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u0016L\u0000\btypeNameq\u0000~\u0000\u0016L\u0000\nw"
+"hiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xp"
+"t\u0000\u001fhttp://www.syndesis.com/NN/XSNNt\u0000\rnormal_stringsr\u00005com.su"
+"n.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr"
+"\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000"
+"\u0000sr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwa"
+"ysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~"
+"\u0000\u0017t\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringq\u0000~\u0000\u001e\u0001q\u0000~\u0000\"t\u0000\t"
+"maxLength\u0000\u0000\u0000@sr\u00000com.sun.msv.grammar.Expression$NullSetExpre"
+"ssion\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0"
+"t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0016L\u0000\fnamespaceURIq\u0000~\u0000\u0016xpq\u0000~\u0000\u001bq\u0000~\u0000\u001as"
+"r\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0001\u00e31yppsr\u0000 c"
+"om.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tname"
+"Classq\u0000~\u0000\txq\u0000~\u0000\u0003\u0001\u00e31nsr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005value"
+"xp\u0000psq\u0000~\u0000\r\u0000A\u009d\u0015ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000 q\u0000~\u0000#t\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSp"
+"aceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001dq\u0000~\u0000\'sq\u0000~\u0000(q\u0000~\u00003q\u0000~\u0000#s"
+"r\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNam"
+"eq\u0000~\u0000\u0016L\u0000\fnamespaceURIq\u0000~\u0000\u0016xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-insta"
+"ncesr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000.\u0001psq\u0000~\u00007t\u0000\u0004Namet\u0000\u0000sq\u0000~\u0000*\u0001e\u00f2\u00b4ppsr\u0000 com."
+"sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gramm"
+"ar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0001e\u00f2\u00a9q\u0000~\u0000/psq\u0000~\u0000\b\u0001e\u00f2\u00a6q"
+"\u0000~\u0000/p\u0000sq\u0000~\u0000\u0000\u0001e\u00f2\u009bppsq\u0000~\u0000\b\u0000\u001e\u00ecWpp\u0000sq\u0000~\u0000*\u0000\u001e\u00ecLppsq\u0000~\u0000C\u0000\u001e\u00ecAq\u0000~\u0000/ps"
+"q\u0000~\u0000,\u0000\u001e\u00ec>q\u0000~\u0000/psr\u00002com.sun.msv.grammar.Expression$AnyStringE"
+"xpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bq\u0000~\u0000>q\u0000~\u0000Msr\u0000 com.sun.msv.gram"
+"mar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u00008q\u0000~\u0000=sq\u0000~\u00007t\u0000Acom.sbc.eia.b"
+"is.embus.service.netprovision.interfaces.ElementItemt\u0000+http:"
+"//java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000*\u0001G\u0006?ppsq\u0000~\u0000,\u0001G\u00064"
+"q\u0000~\u0000/pq\u0000~\u00000sq\u0000~\u00007q\u0000~\u0000:q\u0000~\u0000;q\u0000~\u0000=sq\u0000~\u00007t\u0000\u0007Elementq\u0000~\u0000Aq\u0000~\u0000=sq"
+"\u0000~\u0000*\u0001\u007f\u00ad{ppsq\u0000~\u0000C\u0001\u007f\u00adpq\u0000~\u0000/psq\u0000~\u0000\b\u0001\u007f\u00admq\u0000~\u0000/p\u0000sq\u0000~\u0000\u0000\u0001\u007f\u00adbppsq\u0000~\u0000"
+"\b\u0000\u001e\u00ecWpp\u0000sq\u0000~\u0000*\u0000\u001e\u00ecLppsq\u0000~\u0000C\u0000\u001e\u00ecAq\u0000~\u0000/psq\u0000~\u0000,\u0000\u001e\u00ec>q\u0000~\u0000/pq\u0000~\u0000Mq\u0000~"
+"\u0000Oq\u0000~\u0000=sq\u0000~\u00007t\u0000@com.sbc.eia.bis.embus.service.netprovision.i"
+"nterfaces.ObjectItemq\u0000~\u0000Rsq\u0000~\u0000*\u0001`\u00c1\u0006ppsq\u0000~\u0000,\u0001`\u00c0\u00fbq\u0000~\u0000/pq\u0000~\u00000sq"
+"\u0000~\u00007q\u0000~\u0000:q\u0000~\u0000;q\u0000~\u0000=sq\u0000~\u00007t\u0000\u0006Objectq\u0000~\u0000Aq\u0000~\u0000=sq\u0000~\u0000*\u0002\u0000&\rppsq\u0000~"
+"\u0000,\u0002\u0000&\u0002q\u0000~\u0000/psq\u0000~\u0000\r\u0001~\u0084jppsr\u0000 com.sun.msv.datatype.xsd.IntType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.IntegerDerivedType\u0099\u00f1"
+"]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetsq\u0000~\u0000\u0014xq\u0000~\u0000 q\u0000~\u0000#t\u0000\u0003intq\u0000~\u00005sr\u0000*com.sun"
+".msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.ms"
+"v.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljava/la"
+"ng/Object;xq\u0000~\u0000\u0012ppq\u0000~\u00005\u0000\u0001sr\u0000*com.sun.msv.datatype.xsd.MinInc"
+"lusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000oppq\u0000~\u00005\u0000\u0000sr\u0000!com.sun.msv.datatyp"
+"e.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000kq\u0000~\u0000#t\u0000\u0004longq\u0000~\u00005sq\u0000~\u0000nppq\u0000~\u0000"
+"5\u0000\u0001sq\u0000~\u0000rppq\u0000~\u00005\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.IntegerType\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000kq\u0000~\u0000#t\u0000\u0007integerq\u0000~\u00005sr\u0000,com.sun.msv.datatype."
+"xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com.sun.msv.da"
+"tatype.xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~"
+"\u0000\u0013ppq\u0000~\u00005\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000 q\u0000~\u0000#t\u0000\u0007decimalq\u0000~\u00005q\u0000~\u0000\u0080t\u0000\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000zt\u0000"
+"\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.l"
+"ang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000zt\u0000\fmaxInclusivesq\u0000~\u0000\u0084\u007f\u00ff\u00ff"
+"\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000uq\u0000~\u0000\u0083sr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u0000"
+"\u0085\u0080\u0000\u0000\u0000q\u0000~\u0000uq\u0000~\u0000\u0087sq\u0000~\u0000\u0089\u007f\u00ff\u00ff\u00ffq\u0000~\u0000\'sq\u0000~\u0000(q\u0000~\u0000mq\u0000~\u0000#sq\u0000~\u00007t\u0000\u0005Index"
+"q\u0000~\u0000Aq\u0000~\u0000=sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L"
+"\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;"
+"xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002"
+"\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000\u0090[\u0000\u0005tablet\u0000![Lcom/sun/msv"
+"/grammar/Expression;xp\u0000\u0000\u0000\u0012\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Exp"
+"ression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppq\u0000~\u0000\u0005ppppppq\u0000~\u0000bppppppp"
+"pppq\u0000~\u0000Gpppppppppppppq\u0000~\u0000Eppppppq\u0000~\u0000Jq\u0000~\u0000^ppq\u0000~\u0000Bppppppq\u0000~\u0000I"
+"q\u0000~\u0000]ppq\u0000~\u0000\fppppppq\u0000~\u0000\u0006ppppppppppppppppq\u0000~\u0000+ppppppq\u0000~\u0000[ppppp"
+"ppppppppq\u0000~\u0000Yppppppppppq\u0000~\u0000Xppq\u0000~\u0000gpppppppppppq\u0000~\u0000\u0007pppppq\u0000~\u0000"
+"Sppppppppppppppppppppppppppppppppppppppppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        if (("Element" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        if (("Object" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  12 :
                        if (("Object" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  7 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("Name" == ___local)&&("" == ___uri)) {
                            _Element.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl.class), 8, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        break;
                    case  10 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("Name" == ___local)&&("" == ___uri)) {
                            _Object.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  6 :
                        if (("Element" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  3 :
                        if (("Name" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Index = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Index = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  5 :
                        if (("Name" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  10 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  11 :
                        if (("Object" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  8 :
                        if (("Element" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  12 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        if (("Index" == ___local)&&("" == ___uri)) {
                            _Element.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl) spawnChildFromEnterAttribute((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl.class), 8, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  10 :
                        if (("Index" == ___local)&&("" == ___uri)) {
                            _Object.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl) spawnChildFromEnterAttribute((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl.class), 11, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  0 :
                        if (("Index" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  12 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  7 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  10 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  2 :
                        if (("Index" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  9 :
                            state = 12;
                            continue outer;
                        case  12 :
                            revertToParentFromText(value);
                            return ;
                        case  7 :
                            attIdx = context.getAttribute("", "Index");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  10 :
                            attIdx = context.getAttribute("", "Index");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "Index");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                eatText1(v);
                                state = 3;
                                continue outer;
                            }
                            state = 3;
                            continue outer;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  4 :
                            eatText2(value);
                            state = 5;
                            return ;
                        case  1 :
                            eatText1(value);
                            state = 2;
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Name = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
