## Sandbox - Python

Collection of python code snippets and examples

Recommended to use PyCharm IDE for development.

### Running Unit Tests

Recommendation is to just use PyCharm if you can. Otherwise...

#### CLI

See: https://stackoverflow.com/questions/1896918/running-unittest-with-typical-test-directory-structure

Folders will often have a structure similar to:

```
my_directory
├── antigravity
│   ├── __init__.py         # make it a package
│   └── arrays.py
└── test
    ├── __init__.py         # also make test a package
    └── test_arrays.py
```

To execute the test file you can use the `unittest` CLI

`python -m unittest test/test_arrays.py`