# Inteligjenca artificiale: Projekti 3
*Studentet: Eljesa Kqiku, Rinesë Morina, Rona Latifaj* 
### Detyra e zgjedhur:
Problemi “Traffic lights” nga linku i cituar në kërkesën e detyrës së postuar në classroom: https://www.csplib.org/Problems/prob016/. Kjo detyrë merr në konsideratë problemin e një kryçëzimi rrugor, i përbëre nga 4 drita trafiku (4 për makinat dhe 4 për këmbësorët). Gjendjet e dritave janë të dhëna në përshkrimin e detyrës.
### Algoritmi i implementimit:
Kërkojmë që këtë problem ta zgjidhim me algoritmin hill climbing. Kurse krahasimi mund te behet me zgjidhjet e deritanishme të detyrës të postuara në ëebfaqe.
### Dataset:
Gjenrimi i dataset-it të problemit ne fjalë do të bëhet si detyrë e grupit, pasiqë bashkësia e ngjarjeve është relativisht e vogël dhe e parashikueshme, por gjithsesi varet nga faktori i ngarkesës së trafikut.
### Constrains:
Disa shembuj ilustrues
Hard constraints: të mos vihet në rrezik në asnjë mundësi jeta e këmbësorëve
Soft constraints: mundësisht të mos ketë intervala më të gjata se 70 sekonda të qëndrimit të dritës së kuqe
### Ide shtesë:
Të përdoret një faktori i ngarkesës së trafikur për ta mundësuar ndryshimin e orarit të dritave të trafikur duke i bazuar në ngarkesën e trafikut të makinave/këmbësorëvë dhe kohën e ditës përkatëse.
