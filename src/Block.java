public class Block {
        private Cell[][] blockShape;
        private String textDir;
        enum BlockShape{

            ONE, TWO, MUSTAFA, LE, THREE, EGE, SQUARE, ZEGO, PLUS, U;

        }
        public Block( BlockShape blockName){
            blockShape = new Cell[3][3];
            for( int i = 0; i <3; i++){
                for(int j = 0; j < 3; j++){
                    blockShape[i][j] = new Cell();
                }
            }
            enumSelection(blockName);
        }
//aaaaaa
        private void enumSelection(BlockShape block){
            if(block == BlockShape.ONE)
                this.blockShape[1][1].setVisible(true);
            else if(block == BlockShape.TWO){
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
            }
            else if(block == BlockShape.MUSTAFA){
                this.blockShape[0][1].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
                this.blockShape[1][2].setVisible(true);
            }
            else if(block == BlockShape.LE){
                this.blockShape[0][0].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
                this.blockShape[1][2].setVisible(true);
            }
            else if(block == BlockShape.THREE){
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
                this.blockShape[1][2].setVisible(true);
            }
            else if(block == BlockShape.EGE){
                this.blockShape[1][2].setVisible(true);
                this.blockShape[2][1].setVisible(true);
            }
            else if(block == BlockShape.SQUARE){
                this.blockShape[0][0].setVisible(true);
                this.blockShape[0][1].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
            }
            else if(block == BlockShape.ZEGO){
                this.blockShape[0][1].setVisible(true);
                this.blockShape[0][2].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
            }
            else if(block == BlockShape.PLUS){
                this.blockShape[0][1].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
                this.blockShape[1][2].setVisible(true);
                this.blockShape[2][1].setVisible(true);
            }
            else if(block == BlockShape.U){
                this.blockShape[0][0].setVisible(true);
                this.blockShape[0][2].setVisible(true);
                this.blockShape[1][0].setVisible(true);
                this.blockShape[1][1].setVisible(true);
                this.blockShape[1][2].setVisible(true);
            }
        }

        public Cell[][] getBlockShape(){
            return blockShape;
        }



}


