package br.com.aluraFlix.infra;

public interface Mapper<S, T> {
    T map(S source);
}
