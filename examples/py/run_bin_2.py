import os
import subprocess

print(os.getenv('PYTHONPATH'))
print(len(os.getenv('PYTHONPATH')))

bin = os.path.join(os.path.dirname(__file__), 'run_bin')
subprocess.run(bin)

print(os.getenv('PYTHONPATH'))
print(len(os.getenv('PYTHONPATH')))

s = set()
dup_c = 0
for path in os.getenv('PYTHONPATH').split(':'):
    print(path)
    if path in s:
        dup_c += 1
    s.add(path)

print(dup_c)
