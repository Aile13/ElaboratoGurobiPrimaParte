\ LP file per definizione problema ottimizzazione di elaborato
\ Variabili tutte continue

Minimize w

Subject To
  c_di_w_e_x1: w - x1 >= 0
  c_di_w_e_x2: w - x2 >= 0
  c_di_w_e_x3: w - x3 >= 0
  c_di_w_e_x4: w - x4 >= 0
  c_di_w_e_x5: w - x5 >= 0
  c_di_w_e_x6: w - x6 >= 0
  c_di_w_e_x7: w - x7 >= 0
  c_di_w_e_x8: w - x8 >= 0
  c_di_w_e_x9: w - x9 >= 0
  c_di_w_e_x10: w - x10 >= 0
  c_di_w_e_x11: w - x11 >= 0
  c_di_w_e_x12: w - x12 >= 0
  c_di_w_e_x13: w - x13 >= 0
  c_di_w_e_x14: w - x14 >= 0
  c_di_w_e_x15: w - x15 >= 0
  c_di_w_e_x16: w - x16 >= 0

  c_di_x1_e_omega: x1 - 3,00 >= 0
  c_di_x1_e_teta: x1 <= 17,00
  c_di_x2_e_omega: x2 - 3,00 >= 0
  c_di_x2_e_teta: x2 <= 17,00
  c_di_x3_e_omega: x3 - 3,00 >= 0
  c_di_x3_e_teta: x3 <= 17,00
  c_di_x4_e_omega: x4 - 3,00 >= 0
  c_di_x4_e_teta: x4 <= 17,00
  c_di_x5_e_omega: x5 - 3,00 >= 0
  c_di_x5_e_teta: x5 <= 17,00
  c_di_x6_e_omega: x6 - 3,00 >= 0
  c_di_x6_e_teta: x6 <= 17,00
  c_di_x7_e_omega: x7 - 3,00 >= 0
  c_di_x7_e_teta: x7 <= 17,00
  c_di_x8_e_omega: x8 - 3,00 >= 0
  c_di_x8_e_teta: x8 <= 17,00
  c_di_x9_e_omega: x9 - 3,00 >= 0
  c_di_x9_e_teta: x9 <= 17,00
  c_di_x10_e_omega: x10 - 3,00 >= 0
  c_di_x10_e_teta: x10 <= 17,00
  c_di_x11_e_omega: x11 - 3,00 >= 0
  c_di_x11_e_teta: x11 <= 17,00
  c_di_x12_e_omega: x12 - 3,00 >= 0
  c_di_x12_e_teta: x12 <= 17,00
  c_di_x13_e_omega: x13 - 3,00 >= 0
  c_di_x13_e_teta: x13 <= 17,00
  c_di_x14_e_omega: x14 - 3,00 >= 0
  c_di_x14_e_teta: x14 <= 17,00
  c_di_x15_e_omega: x15 - 3,00 >= 0
  c_di_x15_e_teta: x15 <= 17,00
  c_di_x16_e_omega: x16 - 3,00 >= 0
  c_di_x16_e_teta: x16 <= 17,00

  c_delle_xi_somma_delle_percentuali: x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14 + x15 + x16 = 100,0

  c_di_x1_tempo_max_esec:  12,73 x1 <= 77
  c_di_x2_tempo_max_esec:  9,79 x2 <= 77
  c_di_x3_tempo_max_esec:  24,20 x3 <= 77
  c_di_x4_tempo_max_esec:  4,08 x4 <= 77
  c_di_x5_tempo_max_esec:  4,87 x5 <= 77
  c_di_x6_tempo_max_esec:  4,39 x6 <= 77
  c_di_x7_tempo_max_esec:  4,17 x7 <= 77
  c_di_x8_tempo_max_esec:  3,39 x8 <= 77
  c_di_x9_tempo_max_esec:  19,83 x9 <= 77
  c_di_x10_tempo_max_esec:  24,25 x10 <= 77
  c_di_x11_tempo_max_esec:  18,61 x11 <= 77
  c_di_x12_tempo_max_esec:  4,15 x12 <= 77
  c_di_x13_tempo_max_esec:  25,04 x13 <= 77
  c_di_x14_tempo_max_esec:  7,85 x14 <= 77
  c_di_x15_tempo_max_esec:  7,09 x15 <= 77
  c_di_x16_tempo_max_esec:  7,18 x16 <= 77

Bounds
  0,0 <= x1 <= 100,0
  0,0 <= x2 <= 100,0
  0,0 <= x3 <= 100,0
  0,0 <= x4 <= 100,0
  0,0 <= x5 <= 100,0
  0,0 <= x6 <= 100,0
  0,0 <= x7 <= 100,0
  0,0 <= x8 <= 100,0
  0,0 <= x9 <= 100,0
  0,0 <= x10 <= 100,0
  0,0 <= x11 <= 100,0
  0,0 <= x12 <= 100,0
  0,0 <= x13 <= 100,0
  0,0 <= x14 <= 100,0
  0,0 <= x15 <= 100,0
  0,0 <= x16 <= 100,0

End
