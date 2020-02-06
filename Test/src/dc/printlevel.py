class TreeNode:
    left = None
    right = None
    def __init__(self, v):
        self.v = v

def init():
    root = TreeNode('*')
    root.left = TreeNode('a')
    root.right = TreeNode('b')
    root.left.left = TreeNode('c')
    root.left.left.left = TreeNode('d')
    root.right.left = TreeNode('e')
    root.right.right = TreeNode('f')
    return root

def print_level():
    res = init()
    queue = []
    queue.append(res)
    while(queue) :
        e = queue.pop(0)
        print(e.v)
        if e.left!=None:
            queue.append(e.left)
        if e.right!=None:
            queue.append(e.right)



print_level()
