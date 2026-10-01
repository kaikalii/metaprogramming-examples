def make_vector(name: str, fields: list[str]):
    return f"""
class {name}:
    ZERO: any
    {"\n    ".join(f"{field}: any" for field in fields)}
    def __init__(self, {", ".join(fields)}):
        {"\n        ".join(f"self.{field} = {field}" for field in fields)}
    def splat(x):
        return {name}({"x," * len(fields)})
    def length_squared(self):
        return {" + ".join(f"self.{field} ** 2" for field in fields)}
    def length(self):
        return self.length_squared() ** 0.5
    def normalize(self):
        len = self.length()
        return 0 if len == 0 else self.div(len)
    def add(self, b):
        return {name}({", ".join(f"self.{field} + b.{field}" for field in fields)})
    def sub(self, b):
        return {name}({", ".join(f"self.{field} - b.{field}" for field in fields)})
    def mul(self, b):
        return {name}({", ".join(f"self.{field} * b" for field in fields)})
    def div(self, b):
        return {name}({", ".join(f"self.{field} / b" for field in fields)})
{name}.ZERO = {name}({"0," * len(fields)})
{"\n".join(f"{name}.{field} = {name}({", ".join("1" if field == field2 else "0" for field2 in fields)})" for field in fields)}
    """


exec(make_vector("Size", ["width", "height"]))  # noqa: S102
exec(make_vector("Vec2", ["x", "y"]))  # noqa: S102
exec(make_vector("Vec3", ["x", "y", "z"]))  # noqa: S102
exec(make_vector("Vec4", ["x", "y", "z", "w"]))  # noqa: S102

print(Size(1920, 1080).__dict__)  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print(Vec2(3, 4).length())  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print(Vec3(3, 4, 12).length())  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print(Vec4(3, 4, 12, 84).length())  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print(Vec3.splat(5).__dict__)  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print((Vec2(1, 2).add(Vec2(3, 4))).__dict__)  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print(Vec2(3, 4).normalize().__dict__)  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
print((Vec2.x.add(Vec2.y.mul(2))).__dict__)  # noqa: F821  # pyright: ignore[reportUndefinedVariable]
