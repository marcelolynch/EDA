package tp2_eda;

public interface Injector<A,B> {
	B apply(B seed, A a);
}
