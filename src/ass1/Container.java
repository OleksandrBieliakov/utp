package ass1;

import java.util.List;

public class Container<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult>
        implements IContainer<TElement, TAggregateResult> {

    private List<TElement> elements;

    Container(List<TElement> elements) {
        this.elements = elements;
    }

    @Override
    public List<TElement> elements() {
        return elements;
    }

    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult intermediateResult = null;
        for (TElement element : elements) {
            intermediateResult = element.aggregate(intermediateResult);
        }
        return intermediateResult;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        return elements.get(index).deepClone();
    }

    @Override
    public TElement get(int index) {
        return elements.get(index);
    }
}
