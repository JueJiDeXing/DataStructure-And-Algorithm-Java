package 数据结构.设计数据结构;

import java.util.HashMap;
import java.util.Map;

public class _2671频率跟踪器 {
}

class FrequencyTracker {
    Map<Integer, Integer> numToFreq = new HashMap<>();//统计num的频率
    Map<Integer, Integer> freqToCount = new HashMap<>();//统计频率对应的个数

    public FrequencyTracker() {

    }

    public void add(int number) {
        if (!numToFreq.containsKey(number)) {
            numToFreq.put(number, 1);
            freqToCount.put(1, freqToCount.getOrDefault(1, 0) + 1);
        } else {
            int freq = numToFreq.get(number);
            numToFreq.put(number, freq + 1);
            int count = freqToCount.getOrDefault(freq, 0);
            freqToCount.put(freq, count == 0 ? 0 : count - 1);
            freqToCount.put(freq + 1, freqToCount.getOrDefault(freq + 1, 0) + 1);
        }
    }


    public void deleteOne(int number) {
        int freq = numToFreq.getOrDefault(number, 0);
        if (freq == 0) {
            return;
        }
        numToFreq.put(number, freq - 1);
        freqToCount.put(freq, freqToCount.get(freq) - 1);
        freqToCount.put(freq - 1, freqToCount.getOrDefault(freq - 1, 0) + 1);
    }

    public boolean hasFrequency(int frequency) {
        return freqToCount.getOrDefault(frequency, 0) != 0;
    }
}
