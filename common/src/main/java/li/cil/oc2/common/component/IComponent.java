package li.cil.oc2.common.component;

public interface IComponent {
    // Wrap to a native capability/block API
    public Object getKey();
    public Object wrap();
}
