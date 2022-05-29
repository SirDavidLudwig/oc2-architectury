package li.cil.oc2.common.component;

@FunctionalInterface
public interface IComponentCollector {
    void offer(IComponent component);
}
