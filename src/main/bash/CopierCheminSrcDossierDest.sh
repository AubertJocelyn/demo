IFS='§' read -r -a preCheminsImages <<< "$2"

cheminsImages=()

for preChemin in "${preCheminsImages[@]}"; do
  cheminsImages+=("$preChemin")
done

cp "${cheminsImages[@]}" $1