def courses_to_take(course_to_prereqs):
    # Copy list values into a set for faster removal.
    course_to_prereqs = {c: set(p) for c, p in course_to_prereqs.items()}

    todo = [c for c, p in course_to_prereqs.items() if not p]

    # Used to find courses D which have C as a prerequiste
    prereq_to_coures = {}
    for course in course_to_prereqs:
        for prereq in course_to_prereqs[course]:
            if prereq not in prereq_to_coures:
                prereq_to_coures[prereq] = []

            prereq_to_coures[prereq].append(course)
    print('interim result :', prereq_to_coures)
    result = [] # courses we need to take in order

    while todo:
        prereq = todo.pop()
        result.append(prereq)

        # Find which courses are now free to take

        for c in prereq_to_coures.get(prereq, []):
            course_to_prereqs[c].remove(prereq)
            if not course_to_prereqs[c]:
                todo.append(c)

    # Cicrcular dependency
    if len(result) < len(course_to_prereqs):
        return None
    return result

course = { 'C1':[], 'C2' : ['C1'], 'C3':['C1','C2']}
courses_to_take(course)