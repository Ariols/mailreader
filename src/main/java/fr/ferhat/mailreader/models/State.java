package fr.ferhat.mailreader.models;

import org.springframework.data.annotation.Immutable;

@Immutable
public class State {
    private String label;
    private Long value;

    public State(String label, Long value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
