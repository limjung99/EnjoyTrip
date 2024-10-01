package com.enjoytrip.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.enjoytrip.dto.Trip;

public class TripRoute {
    List<Trip> trips;
    int n;
    int END;
    double[][] dp;
    double[][] graph;
    List<Integer> path;
    public TripRoute(List<Trip> trips){
        this.trips = trips;
        n = trips.size();
        END = (1<<n)-1;
        dp = new double[n][END+1];
        graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        path = new ArrayList<Integer>();
    }
    public List<Trip> findTsp() {
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                graph[i][j] = dist(trips, i, j);
                graph[j][i] = graph[i][j];
            }
        }
        tsp(0, 1);
        printPath(0, 1);
        List<Trip> tspTrips = new ArrayList<Trip>();
        for(int i: path){
            tspTrips.add(trips.get(i));
        }
        
        return tspTrips;
    }

    private double tsp(int now, int visited){
        if(visited == END){
            return graph[now][0];
        }
        if(dp[now][visited] != -1) return dp[now][visited];
        dp[now][visited] = 100000;

        for(int i=0;i<n;i++) {
            if((visited & (1<<i)) == 0) {
                dp[now][visited] = Math.min(tsp(i, visited | (1 << i)) + graph[now][i], dp[now][visited]);
            }
        }
        return dp[now][visited];
    }

    private double dist(List<Trip> trips, int i, int j) {
        return Math.pow(trips.get(i).getLatitude() - trips.get(j).getLatitude(), 2)
                + Math.pow(trips.get(i).getLongitude() - trips.get(j).getLongitude(), 2);
    }
    private void printPath(int now, int visited){
        path.add(now);
        if(visited == END)
            return;
            
        double nextValue = 1000000;
        int nextIdx= 0;
        for(int i =0; i<n; i++){
            if((visited & (1<<i)) != 0) continue;
            if(graph[now][i]+dp[i][visited|(1<<i)] < nextValue){
                nextValue = graph[now][i]+dp[i][visited|(1<<i)];
                nextIdx = i;
            }
        } 
        printPath(nextIdx, visited|(1<<nextIdx));
    } 
}
