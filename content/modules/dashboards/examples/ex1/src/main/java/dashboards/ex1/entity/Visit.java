package dashboards.ex1.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "SAMPLE_VISIT", indexes = {
        @Index(name = "IDX_VISIT_PET_ID", columnList = "PET_ID"),
        @Index(name = "IDX_VISIT_VET_ID", columnList = "VET_ID")
})
@Entity(name = "sample_Visit")
public class Visit {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @JoinColumn(name = "PET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    @JoinColumn(name = "VET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vet vet;

    @Transient
    @JmixProperty
    public String getCaption() {
        if(pet!=null && vet!=null){
            return pet.getName()+" "+vet.getName();
        }else return "";
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}