package 数据结构实现.栈;
/**
 <div color=rgb(155,200,80)>
 <h1>栈-接口</h1>
 </div>
 */
public interface MyStack<E> {
    /**
     <div color=rgb(155,200,80)>
     <h1>向栈顶压入元素</h1>
     @param value 待压入值
     @return 是否压入成功
     */
    boolean push(E value);
    /**
     <div color=rgb(155,200,80)>
     <h1>从栈顶弹出元素</h1>
     @return 返回栈顶值,如果为空返回null
     */
    E pop();
    /**
     <div color=rgb(155,200,80)>
     <h1>获取栈顶值</h1>
     @return 返回栈顶值,如果为空返回null
     */
    E peek();
    /**
     <div color=rgb(155,200,80)>
     <h1>检查栈是否为空</h1>
     @return 空栈返回true,否则返回false
     */
    boolean isEmpty();
    /**
     <div color=rgb(155,200,80)>
     <h1>检查栈是否为满</h1>
     @return 满栈返回true,否则返回false
     */
    boolean isFull();
}
