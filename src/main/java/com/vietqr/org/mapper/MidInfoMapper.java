package com.vietqr.org.mapper;

public class MidInfoMapper {
    private String name;
    private String vso;
    private String nationalId;

    public MidInfoMapper() {
    }

    public MidInfoMapper(String name, String vso, String nationalId) {
        this.name = name;
        this.vso = vso;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVso() {
        return vso;
    }

    public void setVso(String vso) {
        this.vso = vso;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
