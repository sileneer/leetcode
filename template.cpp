#include <bits/stdc++.h>

class Solution {
public:
    std::vector<int> twoSum(std::vector<int>& nums, int target)
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return {i, j};
                }
            }
        }
        return {};
    }
};

int main() {
    int n;
  	std::cin >> n;
    std::vector<int> nums;
    for (int i = 0; i < n; i++)
		std::cin >> nums[i];
    int target;
  	std::cin >> target;

    Solution mySolution;	

    // If function return is int

    // cout << solution.twoSum(nums, target) << endl;

    // If function return is vector<int>

    std::vector<int> res =  mySolution.twoSum(nums, target);
    for (int re : res) {
        std::cout << re << " ";
    }

    // If function return is vector<vector<int>>

    int N = 5, M = 6;
    std::vector<std::vector<int> > obj(N, std::vector<int>(M));
    for (auto & i : obj)
    {
        for (int j : i)
        {
            std::cout << j << " ";
        }
        std::cout << "\n";
    }
}