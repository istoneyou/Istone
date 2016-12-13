package com.stone.njubbs.Utils;

import android.util.Log;

/**
 * Created by Stone on 2016/12/13.
 */

public class SortUtils {
    public void maopaoSort(int[] a) {
        Log.v("stone", "maopao");
        int n = a.length;
        int temp =0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n -1- i; j++) {
                if (a[j] > a[j+1]) {
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Log.v("stone", "" + a[i]);
        }
    }

    public void selectSort(int[] a) {
        Log.v("stone", "selectSort");
        int n = a.length;
        int temp, position;
        for (int i = 0; i < n; i ++) {
            temp = a[i];
            position = i;
            for (int j = i + 1; j < n; j++) {
                if (temp > a[j]) {
                    temp = a[j];
                    position = j;
                }
            }
            a[position] = a[i];
            a[i] = temp;
        }
        for (int i = 0; i < n; i++) {
            Log.v("stone", "" + a[i]);
        }
    }

    public void insertSort(int[] a) {
        Log.v("stone", "insertSort");
        int n = a.length;
        int temp, position;
        for (int i = 1; i <n; i ++) {
            temp = a[i];
            position = i;
            for (int j = i-1; j >= 0; j--) {
                if (a[j] > temp) {
                    a[j+1] = a[j];
                    position = j;
                } else {
                    break;
                }
            }
            a[position] = temp;
        }
        for (int i = 0; i < n; i++) {
            Log.v("stone", "" + a[i]);
        }
    }

    public void bInsertSort(int[] a) {
        Log.v("stone", "bInsertSort--------------");
        int n = a.length;
        int temp, left, mid, right;
        for(int i = 1; i < n; i++) {
            temp = a[i];
            left = 0;
            right = i -1;
            mid = 0;
            while(left <= right) {
                mid = (right + left)/2;
                if (a[mid] > temp) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for(int j = i -1; j >= left; j --) {
                a[j+1] = a[j];
            }
            a[left] = temp;
        }
        for (int i = 0; i < n; i++) {
            Log.v("stone", "" + a[i]);
        }
    }

    private  int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while(low<high){
            //找到比基准元素小的元素位置
            while(low<high && a[high]>=temp){
                high--;
            }
            a[low] = a[high];
            while(low<high && a[low]<=temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }

    private void quickSort(int[] a, int low, int high) {
        if(low<high){ //如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a,low,high);
            quickSort(a, 0, middle-1);
            quickSort(a, middle+1, high);
        }
    }
}
