package pl.baranski.ozpn.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ParametrDTO implements Serializable {
    private static final long serialVersionUID = -4614054834632904083L;
    public static final int TYP_STRING = 1;
    public static final int TYP_DATA = 2;
    public static final int TYP_TAB_INTEGER = 3;
    private int typ;
    private String paramString;
    private Date paramDate;
    private List<Integer> paramList;

    public ParametrDTO() {
        // TODO Auto-generated constructor stub
    }

    public ParametrDTO(String znaki) {
        typ = TYP_STRING;
        paramString = znaki;
    }

    public ParametrDTO(List<Integer> lista) {
        typ = TYP_TAB_INTEGER;
        paramList = lista;
    }

    public ParametrDTO(Date data) {
        typ = TYP_DATA;
        paramDate = data;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public String getParamString() {
        return paramString;
    }

    public void setParamString(String paramString) {
        this.paramString = paramString;
    }

    public Date getParamDate() {
        return paramDate;
    }

    public void setParamDate(Date paramDate) {
        this.paramDate = paramDate;
    }

    public List<Integer> getParamList() {
        return paramList;
    }

    public void setParamList(List<Integer> paramList) {
        this.paramList = paramList;
    }

}
