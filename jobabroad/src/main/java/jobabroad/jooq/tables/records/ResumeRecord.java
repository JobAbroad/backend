/*
 * This file is generated by jOOQ.
 */
package jobabroad.jooq.tables.records;


import javax.annotation.Generated;

import jobabroad.jooq.tables.Resume;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResumeRecord extends UpdatableRecordImpl<ResumeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 673869671;

    /**
     * Setter for <code>jobabroad.resume.id_user</code>.
     */
    public void setIdUser(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>jobabroad.resume.id_user</code>.
     */
    public Integer getIdUser() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>jobabroad.resume.observations</code>.
     */
    public void setObservations(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>jobabroad.resume.observations</code>.
     */
    public String getObservations() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Resume.RESUME.ID_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Resume.RESUME.OBSERVATIONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getIdUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getObservations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getObservations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumeRecord value1(Integer value) {
        setIdUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumeRecord value2(String value) {
        setObservations(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ResumeRecord
     */
    public ResumeRecord() {
        super(Resume.RESUME);
    }

    /**
     * Create a detached, initialised ResumeRecord
     */
    public ResumeRecord(Integer idUser, String observations) {
        super(Resume.RESUME);

        set(0, idUser);
        set(1, observations);
    }
}
