package com.oracle.ca.am.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by nadong on 2017/4/20.
 */
@Entity
@Table(name = "CA_FMW_12C_PBC_LEVEL")
public class PBC implements Serializable {

    private Long bugNum;
    private String workspace;
    private String project;
    private String errorFile;
    private String validationDate;
    private String coord;
    private String errorText;

    @Id
    @Column(name = "BUG_NUM")
    public Long getBugNum() {
        return bugNum;
    }

    public void setBugNum(Long bugNum) {
        this.bugNum = bugNum;
    }


    @Column(name = "WORKSPACE")
    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    @Column(name = "PROJECT")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Column(name = "ERROR_FILE")
    public String getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(String errorFile) {
        this.errorFile = errorFile;
    }

    @Column(name = "VALIDATION_DATE")
    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }

    @Column(name = "COORD")
    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    @Column(name = "ERROR_TEXT")
    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}