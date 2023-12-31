package lilypuree.decorative_winter;

import lilypuree.decorative_winter.core.BlockActivateEventHandler;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DWConstants.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onBlockActivate(PlayerInteractEvent.RightClickBlock event) {
        BlockActivateEventHandler handler = new BlockActivateEventHandler(event.getLevel(), event.getPos(), event.getEntity(), event.getHand(), event.getItemStack());
        InteractionResult result = handler.getResult();
        if (result == InteractionResult.FAIL) {
            event.setUseItem(Event.Result.DENY);
        } else if (result == InteractionResult.SUCCESS) {
            event.setUseBlock(Event.Result.DENY);
            event.setUseItem(Event.Result.DENY);
        }
    }
}
