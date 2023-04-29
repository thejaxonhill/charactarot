import { AppBar, Box, Button, CircularProgress, Container, CssBaseline, Unstable_Grid2 as Grid, Paper, ThemeProvider, Toolbar, Tooltip, Typography, createTheme } from '@mui/material'
import { amber, deepOrange } from '@mui/material/colors'
import { GetServerSidePropsContext } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import client from '../../ts/ApiClient'
import { useState } from 'react'

interface MtgCharacterProps {
  card: any
}

export default function MtgCharacter({ card }: MtgCharacterProps) {
  const [character, setCharacter] = useState();
  const [loading, setLoading] = useState(false);
  const [cardValue, setCardValue] = useState<any>(card);

  const handleBuildCharacter = async () => {
    setLoading(true);
    const res = await client.get('/character/mtg', { params: { card: card.name } })
    setCharacter(res.data);
    setLoading(false);
  }

  const handleCardClick = async () => {
    if (!loading) {
      const res = await client.get('/mtg/card/random');
      const newCard = res.data;
      if (card.name === newCard.name)
        await handleCardClick();
      else {
        setCardValue(newCard);
      }
    }
  }

  return (
    <>
      <Head>
        <title>Charactarot</title>
      </Head>
      <Grid container spacing={3} sx={{ mt: 1 }}>
        {cardValue.imageUrl &&
          <Grid xs={12} sm={12} >
            <Tooltip title={<Typography>{cardValue.text}</Typography>}>
              <Box>
                <Paper onClick={() => handleCardClick()} sx={{ width: 223, height: 311, mx: 'auto', borderRadius: 3, overflow: 'hidden' }}>
                  <Image src={cardValue.imageUrl} alt="favicon.ico" width={223} height={311} />
                </Paper>
                <Typography variant="h5" sx={{ textAlign: 'center' }}>{cardValue.name}</Typography>
              </Box>
            </Tooltip>
          </Grid>
        }
        {character &&
          <Grid sm={8} smOffset={2}>
            <Paper sx={{ p: 1, mt: 1, background: amber[100] }}>
              <Typography >{character}</Typography>
            </Paper>
          </Grid>
        }
      </Grid>
      {loading &&
        <Box sx={{ display: 'flex', my: 1, width: "100%" }}>
          <CircularProgress size={35} sx={{ m: 'auto' }} />
        </Box>
      }
      <Box sx={{ display: 'flex', my: 3, width: "100%" }}>
        <Button onClick={handleBuildCharacter} disabled={true} variant='contained' sx={{ m: 'auto' }}>Build Character</Button>
      </Box>
    </>
  )
}

export async function getServerSideProps(context: GetServerSidePropsContext) {
  try {
    const res = await client.get('/mtg/card/random');
    console.log(res.data);
    return { props: { card: res.data } }
  } catch (error) {
    console.log(error);
    return { props: {} }
  }
}
