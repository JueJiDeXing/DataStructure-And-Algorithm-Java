package 数据结构实现.设计数据结构.缓存;

public class LRU_不定时过期 {
    /*
    每个<key,value>都具有非固定的过期时间, System.currentTimeMillis()+time

    get: 如果存在且未过期, 返回value, 并重置过期时间
         如果不存在或已过期, 返回-1
    put: 如果已存在, 更新value和过期时间
         如果缓存满, 淘汰最久未使用(由于是定时过期,最久未使用==最先过期)
         如果缓存非满, 添加节点
     */

}
