import os

seen_paths = set()
dup_paths = []
for path in os.getenv('PYTHONPATH').split(':'):
  if path in seen_paths:
    dup_paths.append(path)
  seen_paths.add(path)

print(f"Grandchild Process - Found {len(dup_paths)} duplicate paths in PYTHONPATH")
for path in dup_paths:
  print(path)
