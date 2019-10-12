package ass1;

public interface IDeeplyCloneable<TElement extends IDeeplyCloneable<TElement>> {

    TElement deepClone();
}
