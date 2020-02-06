'''
Get all Values at a Certain Height in a Binary Tree 
This problem was recently asked by Amazon:
Given a binary tree, return all values given a certain height h
'''

def print_all_on_level(root, level):
    if root==None or level<0:
        return
    if level==0:
        print(root.value)
        return
    print_all_on_level(root.left, level-1)
    print_all_on_level(root.right, level-1)

def print_all_on_level_itr(root, level):
    if root==None or level<0:
        return
    if level==0:
        print(root.value)
        return
    qu = []
    qu.append(root)

    for i in range(0, level):
        size = len(qu)
        while size>0:
            pop = qu.pop(0)
            if(pop!=None):
                    if pop.left!=None:
                        qu.append(pop.left)
                    if pop.right!=None:
                        qu.append(pop.right)
                    size -= 1

    for s in qu:
        if s!=None:
            print(s.value)

class Node:
    left = None
    right = None
    def __init__(self, v):
        self.value = v
    

root = Node(1) # level : 0
n1 = Node(2) # level : 1
n2 = Node(3) # level : 1
n3 = Node(4) # level : 2
n4 = Node(5) # level : 2
n5 = Node(6) # level : 3
root.left = n1
root.right = n2
n1.left = n3
n2.left = n4
n4.right = n5
print('level 0')
print_all_on_level(root,0)
print('level 1')
print_all_on_level(root,1)
print('level 2')
print_all_on_level(root,2)
print('level 3')
print_all_on_level(root,3)

print('=================iter===============')
print('level 0')
print_all_on_level_itr(root,0)
print('level 1')
print_all_on_level_itr(root,1)
print('level 2')
print_all_on_level_itr(root,2)
print('level 3')
print_all_on_level_itr(root,3)