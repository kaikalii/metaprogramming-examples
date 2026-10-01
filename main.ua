MakeVec! ←^ (
  °⊂ ⊜□⊸≠@ °$"\"_\"" °□₁
  ⊃(∘|⋅($"{_}"/◇⊂₃@ )|∘|⋅⧻|⋅⧻)
  $$ ┌─╴_
  $$   ~ _
  $$   Add ← +
  $$   Sub ← -
  $$   Mul ← ×
  $$   Div ← ÷
  $$   LengthSquared ← $\_/◇+˙×
  $$   Length ← √LengthSquared
  $$   Splat ← °[°_] ↯_
  $$   Zero ← ↯_ 0
  $$   Normalize ← ⨬Div0⊸=0⊸Length
  $$ └─╴
)
MakeVec!"Size Width Height"
MakeVec!"Vec₂ X Y"
MakeVec!"Vec₃ X Y Z"
MakeVec!"Vec₄ X Y Z W"

Size 1920 1080
Vec₂!(Length New 3 4)
Vec₃!(Length New 3 4 12)
Vec₄!(Length New 3 4 12 84)
Vec₃.Splat 5
Vec₂!(Add New 1 2 New 3 4)
Vec₂!(Normalize New 3 4)
