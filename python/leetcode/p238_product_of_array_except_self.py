from typing import List

def product(nums, start_index, end_index, products): # end index is inclusive.
    if start_index == end_index: # termination case, just the value itself
        products[(start_index, end_index)] = nums[start_index]
        return nums[start_index]

    if (start_index, end_index) in products.keys():
        product_res = products[(start_index, end_index)]
    else:
        right = product(nums, start_index + 1, end_index, products)
        product_res = nums[start_index] * right
        products[(start_index, end_index)] = product_res

    return product_res

def product_except_self(nums: List[int]) -> List[int]:

    res = []
    products = {}

    nums_len = len(nums)
    start = 1
    for i in range(0, nums_len):
        if i >= 1:
            start = start * nums[i - 1]

        end = 1
        if i < nums_len - 1:
            end = product(nums, i + 1, nums_len - 1, products)
        factor = start * end
        res.append(factor)

    return res

# Solution taken from leetcode discussions
def product_except_self_optimised(nums):
    n = len(nums)
    output = [1] * n  # Initialize the output array with 1s

    left = 1
    for i in range(n):
        output[i] *= left  # Multiply the current value by the product of elements to the left
        left *= nums[i]  # Update left to include nums[i]

    right = 1
    for i in range(n - 1, -1, -1):
        output[i] *= right  # Multiply the current value by the product of elements to the right
        right *= nums[i]  # Update right to include nums[i]

    return output