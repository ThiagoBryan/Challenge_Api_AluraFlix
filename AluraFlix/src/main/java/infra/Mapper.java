package infra;

public interface Mapper<S, T> {
    T map(S source);
}
