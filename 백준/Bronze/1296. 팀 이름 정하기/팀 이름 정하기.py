main_string = input()
num_candidates = int(input())
candidates_list = sorted([input() for i in range(num_candidates)])
max_percentage = max_index = 0

for index in range(num_candidates):
    L = main_string.count("L") + candidates_list[index].count("L")
    O = main_string.count("O") + candidates_list[index].count("O")
    V = main_string.count("V") + candidates_list[index].count("V")
    E = main_string.count("E") + candidates_list[index].count("E")
    
    percentage = ((L + O) * (L + V) * (L + E) * (O + V) * (O + E) * (V + E)) % 100
    
    if max_percentage < percentage:
        max_percentage = percentage
        max_index = index

print(candidates_list[max_index])